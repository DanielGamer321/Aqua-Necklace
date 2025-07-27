package com.danielgamer321.rotp_an.action.stand;

import com.danielgamer321.rotp_an.entity.stand.stands.AquaNecklaceEntity;
import com.github.standobyte.jojo.action.stand.StandEntityBlock;
import com.github.standobyte.jojo.entity.stand.StandEntity;
import com.github.standobyte.jojo.entity.stand.StandEntityTask;
import com.github.standobyte.jojo.power.impl.stand.IStandPower;

import net.minecraft.world.World;

public class AquaNecklaceBlock extends StandEntityBlock {

    public AquaNecklaceBlock(Builder builder) {
        super(builder);
    }

    @Override
    public void onTaskSet(World world, StandEntity standEntity, IStandPower standPower, Phase phase, StandEntityTask task, int ticks) {
        super.onTaskSet(world, standEntity, standPower, phase, task, ticks);
        if (!world.isClientSide() && ((AquaNecklaceEntity)standEntity).getState() != 2) {
            AquaNecklaceEntity aqua = (AquaNecklaceEntity) standEntity;
            aqua.setState(2);
            aqua.setNoGravity(aqua.standHasNoGravity());
            IStandPower.getStandPowerOptional(aqua.getUser()).ifPresent(stand -> {
                stand.consumeStamina(aqua.getState() == 0 ? 80 : 40);
            });
        }
    }
}
