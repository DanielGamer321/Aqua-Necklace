package com.danielgamer321.rotp_an.action.stand;

import com.danielgamer321.rotp_an.entity.stand.stands.AquaNecklaceEntity;
import com.danielgamer321.rotp_an.init.InitStands;
import com.github.standobyte.jojo.action.ActionConditionResult;
import com.github.standobyte.jojo.action.ActionTarget;
import com.github.standobyte.jojo.action.stand.StandEntityAction;
import com.github.standobyte.jojo.entity.stand.StandEntity;
import com.github.standobyte.jojo.entity.stand.StandEntityTask;
import com.github.standobyte.jojo.power.impl.stand.IStandPower;

import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;

public class AquaNecklaceLeaveTheEntity extends StandEntityAction {
    public AquaNecklaceLeaveTheEntity(Builder builder) {
        super(builder);
    }

    @Override
    protected ActionConditionResult checkSpecificConditions(LivingEntity user, IStandPower power, ActionTarget target) {
        StandEntity stand = power.isActive() ? (StandEntity) power.getStandManifestation() : null;
        if (power.isActive() && stand instanceof AquaNecklaceEntity) {
            AquaNecklaceEntity aqua = (AquaNecklaceEntity) stand;
            if (aqua.isInside())
                return super.checkSpecificConditions(user, power, target);
        }
        return ActionConditionResult.NEGATIVE;
    }

    @Override
    public void standPerform(World world, StandEntity standEntity, IStandPower userPower, StandEntityTask task) {
        if (!world.isClientSide()) {
            if (standEntity instanceof AquaNecklaceEntity){
                AquaNecklaceEntity aqua = (AquaNecklaceEntity) standEntity;
                aqua.setTargetInside(null);
                InitStands.AQUA_NECKLACE_GETTING_INTO_ENTITY.get().setCooldownOnUse(userPower);
            }
        }
    }
}
