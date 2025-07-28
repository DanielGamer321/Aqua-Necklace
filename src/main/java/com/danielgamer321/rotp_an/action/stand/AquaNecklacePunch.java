package com.danielgamer321.rotp_an.action.stand;

import com.danielgamer321.rotp_an.entity.stand.stands.AquaNecklaceEntity;
import com.github.standobyte.jojo.action.Action;
import com.github.standobyte.jojo.action.ActionTarget;
import com.github.standobyte.jojo.action.stand.StandEntityLightAttack;
import com.github.standobyte.jojo.action.stand.punch.StandEntityPunch;
import com.github.standobyte.jojo.entity.stand.StandEntity;
import com.github.standobyte.jojo.entity.stand.StandEntityTask;
import com.github.standobyte.jojo.power.impl.stand.IStandPower;
import com.github.standobyte.jojo.util.mc.damage.StandEntityDamageSource;

import net.minecraft.entity.Entity;
import net.minecraft.world.World;

import java.util.function.Supplier;

public class AquaNecklacePunch extends StandEntityLightAttack {
    private final Supplier<StandEntityLightAttack> normalAttack;

    public AquaNecklacePunch(Builder builder, Supplier<StandEntityLightAttack> normalAttack) {
        super(builder.addExtraUnlockable(normalAttack));
        this.normalAttack = normalAttack != null ? normalAttack : () -> null;
    }

    public AquaNecklacePunch(Builder builder) {
        super(builder);
        this.normalAttack = null;
    }

    @Override
    protected Action<IStandPower> replaceAction(IStandPower power, ActionTarget target) {
        return power.isActive() && power.getStandManifestation() instanceof AquaNecklaceEntity
                && !((AquaNecklaceEntity) power.getStandManifestation()).isInside() && normalAttack.get() != null
                ? normalAttack.get() : this;
    }

    @Override
    public void onTaskSet(World world, StandEntity standEntity, IStandPower standPower, Phase phase, StandEntityTask task, int ticks) {
        super.onTaskSet(world, standEntity, standPower, phase, task, ticks);
        if (!world.isClientSide()) {
            AquaNecklaceEntity aqua = (AquaNecklaceEntity) standEntity;
            if (aqua.getState() != 2) {
                aqua.setState(2);
                aqua.setNoGravity(aqua.standHasNoGravity());
                IStandPower.getStandPowerOptional(aqua.getUser()).ifPresent(stand -> {
                    stand.consumeStamina(aqua.getState() == 0 ? 80 : 40);
                });
            }
            if (aqua.getTargetInside() != null) {
                ActionTarget target = new ActionTarget(aqua.getTargetInside());
                aqua.setTaskTarget(target);
            }
        }
    }

    @Override
    public StandEntityPunch punchEntity(StandEntity stand, Entity target, StandEntityDamageSource dmgSource) {
        if (stand instanceof AquaNecklaceEntity && ((AquaNecklaceEntity)stand).isInside()) {
            dmgSource.bypassArmor();
        }
        return super.punchEntity(stand, target, dmgSource);
    }
}
