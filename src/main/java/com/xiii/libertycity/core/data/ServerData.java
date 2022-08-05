package com.xiii.libertycity.core.data;

public class ServerData implements java.io.Serializable {

    public int globalID;
    public boolean chatStateGlobal = true;
    public boolean chatStateGang = true;
    public boolean chatStatePolice = true;
    public boolean chatStateRP = true;
    public boolean chatStateHRP = true;
    public int chatCooldownGlobal = 2500;

}
