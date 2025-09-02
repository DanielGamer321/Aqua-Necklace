package com.danielgamer321.rotp_an.client;

import com.danielgamer321.rotp_an.RotpAquaAddon;
import com.danielgamer321.rotp_an.client.render.entity.renderer.stand.AquaNecklaceRenderer;
import com.danielgamer321.rotp_an.client.ui.marker.AquaNecklaceMarker;
import com.danielgamer321.rotp_an.init.AddonStands;

import com.github.standobyte.jojo.client.ui.marker.MarkerRenderer;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@EventBusSubscriber(modid = RotpAquaAddon.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientInit {
    
    @SubscribeEvent
    public static void onFMLClientSetup(FMLClientSetupEvent event) {
        RenderingRegistry.registerEntityRenderingHandler(AddonStands.AQUA_NECKLACE.getEntityType(), AquaNecklaceRenderer::new);

        event.enqueueWork(() -> {
            Minecraft mc = event.getMinecraftSupplier().get();

            MarkerRenderer.Handler.addRenderer(new AquaNecklaceMarker(mc));
        });
    }
}
