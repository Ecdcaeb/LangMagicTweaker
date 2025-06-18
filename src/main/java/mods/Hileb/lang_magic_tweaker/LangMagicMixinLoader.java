
package mods.Hileb.lang_magic_tweaker;

import mods.Hileb.lang_magic_tweaker.Tags;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class LangMagicMixinLoader implements zone.rong.mixinbooter.ILateMixinLoader {

    @Override
    public List<String> getMixinConfigs() {
        List<String> str = new ArrayList<>();
        str.add("mixins.lang_magic_tweaker.json");
        return str;
    }
}