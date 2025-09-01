package com.danielgamer321.rotp_an.init;

import com.danielgamer321.rotp_an.RotpAquaAddon;
import com.danielgamer321.rotp_an.action.stand.*;
import com.danielgamer321.rotp_an.entity.stand.stands.AquaNecklaceEntity;
import com.github.standobyte.jojo.init.ModSounds;
import com.github.standobyte.jojo.util.mod.StoryPart;
import com.github.standobyte.jojo.action.Action;
import com.github.standobyte.jojo.action.stand.StandEntityAction;
import com.github.standobyte.jojo.action.stand.StandEntityBlock;
import com.github.standobyte.jojo.action.stand.StandEntityHeavyAttack;
import com.github.standobyte.jojo.action.stand.StandEntityLightAttack;
import com.github.standobyte.jojo.entity.stand.StandEntityType;
import com.github.standobyte.jojo.init.power.stand.EntityStandRegistryObject;
import com.github.standobyte.jojo.power.impl.stand.StandInstance.StandPart;
import com.github.standobyte.jojo.power.impl.stand.stats.StandStats;
import com.github.standobyte.jojo.power.impl.stand.type.EntityStandType;
import com.github.standobyte.jojo.power.impl.stand.type.StandType;

import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;

import static com.github.standobyte.jojo.init.ModEntityTypes.ENTITIES;

public class InitStands {
    @SuppressWarnings("unchecked")
    public static final DeferredRegister<Action<?>> ACTIONS = DeferredRegister.create(
            (Class<Action<?>>) ((Class<?>) Action.class), RotpAquaAddon.MOD_ID);
    @SuppressWarnings("unchecked")
    public static final DeferredRegister<StandType<?>> STANDS = DeferredRegister.create(
            (Class<StandType<?>>) ((Class<?>) StandType.class), RotpAquaAddon.MOD_ID);

    // ======================================== Aqua Necklace ========================================

    public static final RegistryObject<StandEntityLightAttack> AQUA_NECKLACE_PUNCH_OUT = ACTIONS.register("aqua_necklace_punch_out",
            () -> new AquaNecklacePunch(new StandEntityLightAttack.Builder().standUserWalkSpeed(1F)
                    .punchSound(ModSounds.STAND_PUNCH_LIGHT)));

    public static final RegistryObject<StandEntityLightAttack> AQUA_NECKLACE_PUNCH = ACTIONS.register("aqua_necklace_punch",
            () -> new AquaNecklacePunch(new StandEntityLightAttack.Builder().standUserWalkSpeed(1F)
                    .punchSound(ModSounds.STAND_PUNCH_LIGHT), AQUA_NECKLACE_PUNCH_OUT));

    public static final RegistryObject<StandEntityHeavyAttack> AQUA_NECKLACE_BRAIN_ATTACK = ACTIONS.register("aqua_necklace_brain_attack",
            () -> new AquaNecklaceBrainAttack(new StandEntityHeavyAttack.Builder().standUserWalkSpeed(1F)
                    .punchSound(ModSounds.STAND_PUNCH_HEAVY)
                    .partsRequired(StandPart.MAIN_BODY)
                    .shiftVariationOf(AQUA_NECKLACE_PUNCH)));

    public static final RegistryObject<StandEntityHeavyAttack> AQUA_NECKLACE_HEAVY_PUNCH = ACTIONS.register("aqua_necklace_heavy_punch",
            () -> new AquaNecklaceHeavyPunch(new StandEntityHeavyAttack.Builder().standUserWalkSpeed(1F)
                    .punchSound(ModSounds.STAND_PUNCH_HEAVY)
                    .partsRequired(StandPart.ARMS)
                    .setFinisherVariation(AQUA_NECKLACE_BRAIN_ATTACK)
                    .shiftVariationOf(AQUA_NECKLACE_PUNCH)));

    public static final RegistryObject<StandEntityAction> AQUA_NECKLACE_GET_INTO_THE_LUNGS = ACTIONS.register("aqua_necklace_get_into_the_lungs",
            () -> new AquaNecklaceGetIntoTheLungs(new StandEntityAction.Builder().holdType().staminaCostTick(0.25F).standUserWalkSpeed(1F)
                    .resolveLevelToUnlock(3)
                    .partsRequired(StandPart.MAIN_BODY)));

    public static final RegistryObject<StandEntityAction> AQUA_NECKLACE_BLOCK = ACTIONS.register("aqua_necklace_block",
            () -> new AquaNecklaceBlock(new StandEntityBlock.Builder().standUserWalkSpeed(1F)));

    public static final RegistryObject<StandEntityAction> AQUA_NECKLACE_CHANGE_TO_LIQUID = ACTIONS.register("aqua_necklace_change_to_liquid",
            () -> new AquaNecklaceChangeOfState(new StandEntityAction.Builder().staminaCost(40).cooldown(5)
                    .resolveLevelToUnlock(1).isTrained()
                    .partsRequired(StandPart.MAIN_BODY)));

    public static final RegistryObject<StandEntityAction> AQUA_NECKLACE_CHANGE_TO_GASEOUS = ACTIONS.register("aqua_necklace_change_to_gaseous",
            () -> new AquaNecklaceChangeOfState(new StandEntityAction.Builder().staminaCost(80).cooldown(5)
                    .noResolveUnlock()
                    .partsRequired(StandPart.MAIN_BODY)
                    .shiftVariationOf(AQUA_NECKLACE_CHANGE_TO_LIQUID)));

    public static final RegistryObject<StandEntityAction> AQUA_NECKLACE_GETTING_INTO_ENTITY = ACTIONS.register("aqua_necklace_getting_into_entity",
            () -> new AquaNecklaceGettingIntoTheEntity(new StandEntityAction.Builder().staminaCost(40).holdToFire(20, false).cooldown(5)
                    .resolveLevelToUnlock(2)
                    .partsRequired(StandPart.MAIN_BODY)));

    public static final RegistryObject<StandEntityAction> AQUA_NECKLACE_LEAVE_THE_ENTITY = ACTIONS.register("aqua_necklace_leave_the_entity",
            () -> new AquaNecklaceLeaveTheEntity(new StandEntityAction.Builder().cooldown(10)
                    .resolveLevelToUnlock(2)
                    .partsRequired(StandPart.MAIN_BODY)));


    public static final EntityStandRegistryObject<EntityStandType<StandStats>, StandEntityType<AquaNecklaceEntity>> STAND_AQUA_NECKLACE =
            new EntityStandRegistryObject<>("aqua_necklace",
                    STANDS,
                    () -> new EntityStandType.Builder<>()
                            .color(0xB7DAED)
                            .storyPartName(StoryPart.DIAMOND_IS_UNBREAKABLE.getName())
                            .leftClickHotbar(
                                    AQUA_NECKLACE_PUNCH.get(),
                                    AQUA_NECKLACE_GET_INTO_THE_LUNGS.get()
                            )
                            .rightClickHotbar(
                                    AQUA_NECKLACE_BLOCK.get(),
                                    AQUA_NECKLACE_CHANGE_TO_LIQUID.get(),
                                    AQUA_NECKLACE_GETTING_INTO_ENTITY.get()
                            )
                            .defaultStats(StandStats.class, new StandStats.Builder()
                                    .power(8.0)
                                    .speed(8.0, 8.5)
                                    .range(50.0, 75.0)
                                    .durability(15.0, 18.0)
                                    .precision(8.0, 9.0)
                                    .randomWeight(2)
                            )
                            .addSummonShout(null)
                            .addOst(InitSounds.AQUA_NECKLACE_OST)
                            .build(),

                    ENTITIES,
                    () -> new StandEntityType<AquaNecklaceEntity>(AquaNecklaceEntity::new, 0.29F, 0.76F)
                            .summonSound(ModSounds.STAND_SUMMON_DEFAULT)
                            .unsummonSound(ModSounds.STAND_UNSUMMON_DEFAULT))
                    .withDefaultStandAttributes();
}
