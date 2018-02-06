package me.skymc.taboolib.itemnbtapi;

import java.util.HashSet;
import java.util.Set;

import me.skymc.taboolib.message.MsgUtils;

public class NBTListCompound {

    private NBTList owner;
    private Object compound;

    protected NBTListCompound(NBTList parent, Object obj) {
        owner = parent;
        compound = obj;
    }

    public void setString(String key, String value) {
        if (value == null) {
            remove(key);
            return;
        }
        try {
            compound.getClass().getMethod("setString", String.class, String.class).invoke(compound, key, value);
            owner.save();
        } catch (Exception ex) {
             MsgUtils.warn("NBT 操作出现异常: §7" + ex.getMessage());
        }
    }

    public void setInteger(String key, int value) {
        try {
            compound.getClass().getMethod("setInt", String.class, int.class).invoke(compound, key, value);
            owner.save();
        } catch (Exception ex) {
             MsgUtils.warn("NBT 操作出现异常: §7" + ex.getMessage());
        }
    }

    public int getInteger(String value) {
        try {
            return (int) compound.getClass().getMethod("getInt", String.class).invoke(compound, value);
        } catch (Exception ex) {
             MsgUtils.warn("NBT 操作出现异常: §7" + ex.getMessage());
        }
        return 0;
    }

    public void setDouble(String key, double value) {
        try {
            compound.getClass().getMethod("setDouble", String.class, double.class).invoke(compound, key, value);
            owner.save();
        } catch (Exception ex) {
             MsgUtils.warn("NBT 操作出现异常: §7" + ex.getMessage());
        }
    }

    public double getDouble(String key) {
        try {
            return (double) compound.getClass().getMethod("getDouble", String.class).invoke(compound, key);
        } catch (Exception ex) {
             MsgUtils.warn("NBT 操作出现异常: §7" + ex.getMessage());
        }
        return 0;
    }


    public String getString(String key) {
        try {
            return (String) compound.getClass().getMethod("getString", String.class).invoke(compound, key);
        } catch (Exception ex) {
             MsgUtils.warn("NBT 操作出现异常: §7" + ex.getMessage());
        }
        return "";
    }

    public boolean hasKey(String key) {
        try {
            return (boolean) compound.getClass().getMethod("hasKey", String.class).invoke(compound, key);
        } catch (Exception ex) {
             MsgUtils.warn("NBT 操作出现异常: §7" + ex.getMessage());
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    public Set<String> getKeys() {
        try {
            return (Set<String>) compound.getClass().getMethod("c").invoke(compound);
        } catch (Exception ex) {
             MsgUtils.warn("NBT 操作出现异常: §7" + ex.getMessage());
        }
        return new HashSet<>();
    }

    public void remove(String key) {
        try {
            compound.getClass().getMethod("remove", String.class).invoke(compound, key);
        } catch (Exception ex) {
             MsgUtils.warn("NBT 操作出现异常: §7" + ex.getMessage());
        }
    }

}
