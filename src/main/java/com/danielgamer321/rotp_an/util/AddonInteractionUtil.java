package com.danielgamer321.rotp_an.util;

import com.danielgamer321.rotp_an.RotpAquaAddon;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = RotpAquaAddon.MOD_ID)
public class AddonInteractionUtil {
    private static final ResourceLocation THE_HAND_ID = new ResourceLocation("jojo", "the_hand");
    public static boolean isTheHand(Entity entity) {
        if (entity == null) return false;

        EntityType<?> type = entity.getType();
        if (type == null) return false;
        ResourceLocation typeId = type.getRegistryName();
        return THE_HAND_ID.equals(typeId);
    }
}
