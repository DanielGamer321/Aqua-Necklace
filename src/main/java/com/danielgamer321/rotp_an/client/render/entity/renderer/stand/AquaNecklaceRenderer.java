package com.danielgamer321.rotp_an.client.render.entity.renderer.stand;

import com.danielgamer321.rotp_an.RotpAquaAddon;
import com.danielgamer321.rotp_an.client.render.entity.model.stand.AquaNecklaceModel;
import com.danielgamer321.rotp_an.entity.stand.stands.AquaNecklaceEntity;
import com.github.standobyte.jojo.client.render.entity.model.stand.StandModelRegistry;
import com.github.standobyte.jojo.client.render.entity.renderer.stand.StandEntityRenderer;
import com.github.standobyte.jojo.client.standskin.StandSkinsManager;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;

import java.util.Optional;

public class AquaNecklaceRenderer extends StandEntityRenderer<AquaNecklaceEntity, AquaNecklaceModel> {
    private static final ResourceLocation normal = new ResourceLocation(RotpAquaAddon.MOD_ID, "textures/entity/stand/aqua_necklace.png");
    private static final ResourceLocation liquid = new ResourceLocation(RotpAquaAddon.MOD_ID, "textures/entity/stand/aqua_necklace_liquid.png");
    private static final ResourceLocation vapor = new ResourceLocation(RotpAquaAddon.MOD_ID, "textures/entity/stand/aqua_necklace_vapor.png");
    private static ResourceLocation currentTexture = normal;

    public AquaNecklaceRenderer(EntityRendererManager renderManager) {
        super(renderManager,
                StandModelRegistry.registerModel(new ResourceLocation(RotpAquaAddon.MOD_ID, "aqua_necklace"), AquaNecklaceModel::new),
                currentTexture, 0);
    }

    @Override
    public ResourceLocation getTextureLocation(AquaNecklaceEntity entity) {
        switch (entity.getState()) {
            case 0:
                currentTexture = vapor;
                break;
            case 1:
                currentTexture = liquid;
                break;
            default:
                currentTexture = normal;
        }
        return super.getTextureLocation(entity);
    }

    @Override
    public ResourceLocation getTextureLocation(Optional<ResourceLocation> standSkin) {
        return StandSkinsManager.getInstance()
                .getRemappedResPath(manager -> manager.getStandSkin(standSkin), currentTexture);
    }
}
