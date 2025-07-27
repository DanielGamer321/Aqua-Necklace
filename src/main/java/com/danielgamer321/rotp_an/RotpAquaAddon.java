package com.danielgamer321.rotp_an;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danielgamer321.rotp_an.init.InitEntities;
import com.danielgamer321.rotp_an.init.InitSounds;
import com.danielgamer321.rotp_an.init.InitStands;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(RotpAquaAddon.MOD_ID)
public class RotpAquaAddon {
    // The value here should match an entry in the META-INF/mods.toml file
    public static final String MOD_ID = "rotp_an";
    private static final Logger LOGGER = LogManager.getLogger();

    public RotpAquaAddon() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        InitEntities.ENTITIES.register(modEventBus);
        InitSounds.SOUNDS.register(modEventBus);
        InitStands.ACTIONS.register(modEventBus);
        InitStands.STANDS.register(modEventBus);
    }

    public static Logger getLogger() {
        return LOGGER;
    }
}
