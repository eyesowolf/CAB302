package com.example.cab302;

import com.example.cab302.dbmodelling.User;
import com.github.pireba.applescript.*;
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

public class ApplicationTracker {

    private static User currentUser;

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User user) {
        currentUser = user;
    }

    // Existing method to get active window
    public static String getActiveWindow(){
        String result = null;
        interface Psapi extends StdCallLibrary {
            Psapi INSTANCE = (Psapi) Native.loadLibrary("Psapi", Psapi.class);
            WinDef.DWORD GetModuleBaseNameW(Pointer hProcess, Pointer hModule, byte[] lpBaseName, int nSize);
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
        } else if(Platform.isLinux()) {
            result = "ApplicationTrackingNotSupported";
        }
        return result;
    }
}