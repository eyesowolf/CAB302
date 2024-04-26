package com.example.cab302;

import com.sun.jna.Native;
import com.sun.jna.Platform;
import com.sun.jna.Pointer;
import com.sun.jna.platform.unix.X11;
import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinNT;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.win32.StdCallLibrary;
import com.github.pireba.applescript.*;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class ApplicationTracker {
    /**
     * This class detects what operating system the user is using and applies an appropriate method to determine what the "In Focus" windows is.
     * @return
     */
    public static String getActiveWindow(){
        /**
         * This Method is called to return the name of the in focus window as a string. on macOS there is no further data sanitisation needed
         */
        String result = null;
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        interface Psapi extends StdCallLibrary {
            Psapi INSTANCE = (Psapi) Native.loadLibrary("Psapi", Psapi.class);

            WinDef.DWORD GetModuleBaseNameW(Pointer hProcess, Pointer hModule, byte[] lpBaseName, int nSize);
        }
        interface XLib extends StdCallLibrary {
            XLib INSTANCE = (XLib) Native.loadLibrary("XLib", Psapi.class);

            int XGetInputFocus(X11.Display display, X11.Window focus_return, Pointer revert_to_return);
        }
        if (Platform.isWindows()){
            final int PROCESS_VM_READ = 0x0010;
            final int PROCESS_QUERY_INFORMATION = 0x0400;
            final User32 user32 = User32.INSTANCE;
            final Kernel32 kernel32 = Kernel32.INSTANCE;
            final Psapi psapi = Psapi.INSTANCE;
            WinDef.HWND windowHandle = user32.GetForegroundWindow();
            IntByReference pid = new IntByReference();
            user32.GetWindowThreadProcessId(windowHandle, pid);
            WinNT.HANDLE processHandle = kernel32.OpenProcess(PROCESS_VM_READ | PROCESS_QUERY_INFORMATION, true, pid.getValue());

            byte[] filename = new byte[512];
            Psapi.INSTANCE.GetModuleBaseNameW(processHandle.getPointer(), Pointer.NULL,filename,filename.length);
            String name = new String(filename);
            System.out.println(name);
            result = name;
        } else if (Platform.isMac()) {
            try {
                String command[] = new String[4];
                command[0] = "tell application \"System Events\"";
                command[1] = "\tset activeApps to name of application processes whose frontmost is true";
                command[2] = "\tset activeApp to item 1 of activeApps";
                command[3] = "end tell";
                AppleScript as = new AppleScript(command);
                result = as.executeAsString();
            } catch (AppleScriptException e) {
                e.printStackTrace();
            }
        } else if(Platform.isLinux()) {  // Possibly most of the Unix systems will work here too, e.g. FreeBSD
//            final X11 x11 = X11.INSTANCE;
//            final XLib xlib= XLib.INSTANCE;
//            X11.Display display = x11.XOpenDisplay(null);
//            X11.Window window=new X11.Window();
//            xlib.XGetInputFocus(display, window,Pointer.NULL);
//            X11.XTextProperty name=new X11.XTextProperty();
//            x11.XGetWMName(display, window, name);
//            result = name.toString();
            result = "ApplicationTrackingNotSupported";
        }
        return result;
    }
}
