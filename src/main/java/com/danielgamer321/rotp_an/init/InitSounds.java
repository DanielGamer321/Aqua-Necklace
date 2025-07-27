package com.danielgamer321.rotp_an.init;

import com.danielgamer321.rotp_an.RotpAquaAddon;
import com.github.standobyte.jojo.util.mc.OstSoundList;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class InitSounds {
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, RotpAquaAddon.MOD_ID);

    static final OstSoundList AQUA_NECKLACE_OST = new OstSoundList(new ResourceLocation(RotpAquaAddon.MOD_ID, "aqua_necklace_ost"), SOUNDS);

}
