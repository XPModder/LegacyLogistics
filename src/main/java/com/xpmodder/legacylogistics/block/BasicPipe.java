package com.xpmodder.legacylogistics.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;


//Basic Pipes do not have any special functionality and are used for pathfinding to funtional pipes
//Maybe render travelling item inside it, but how?
public class BasicPipe extends Block {

    public static final BooleanProperty NORTH = BlockStateProperties.NORTH;
    public static final BooleanProperty EAST = BlockStateProperties.EAST;
    public static final BooleanProperty SOUTH = BlockStateProperties.SOUTH;
    public static final BooleanProperty WEST = BlockStateProperties.WEST;
    public static final BooleanProperty UP = BlockStateProperties.UP;
    public static final BooleanProperty DOWN = BlockStateProperties.DOWN;


    public static final VoxelShape CENTER_SHAPE = Block.box(4.0d, 4.0d, 4.0d, 12.0d, 12.0d, 12.0d);
    public static final VoxelShape NORTH_SHAPE = Block.box(4.0d, 4.0d, 0.0d, 12.0d, 12.0d, 4.0d);
    public static final VoxelShape EAST_SHAPE = Block.box(12.0d, 4.0d, 4.0d, 16.0d, 12.0d, 12.0d);
    public static final VoxelShape SOUTH_SHAPE = Block.box(4.0d, 4.0d, 12.0d, 12.0d, 12.0d, 16.0d);
    public static final VoxelShape WEST_SHAPE = Block.box(0.0d, 4.0d, 4.0d, 4.0d, 12.0d, 12.0d);
    public static final VoxelShape UP_SHAPE = Block.box(4.0d, 12.0d, 4.0d, 12.0d, 16.0d, 12.0d);
    public static final VoxelShape DOWN_SHAPE = Block.box(4.0d, 0.0d, 4.0d, 12.0d, 4.0d, 12.0d);


    public BasicPipe(Properties properties) {
        super(properties);
        registerDefaultState(this.stateDefinition.any().setValue(NORTH, false).setValue(EAST, false).setValue(SOUTH, false).setValue(WEST, false).setValue(UP, false).setValue(DOWN, false));
    }


    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(NORTH, EAST, SOUTH, WEST, UP, DOWN);
    }


    public boolean canConnectToBlock(BlockState state){
        return state.getBlock() instanceof BasicPipe;
    }


    public BlockState getState(BlockState currentState, LevelAccessor level, BlockPos pos){

        if (canConnectToBlock(level.getBlockState(pos.north()))) {
            currentState = currentState.setValue(NORTH, true);
        }
        else{
            currentState = currentState.setValue(NORTH, false);
        }
        if (canConnectToBlock(level.getBlockState(pos.east()))) {
            currentState = currentState.setValue(EAST, true);
        }
        else{
            currentState = currentState.setValue(EAST, false);
        }
        if (canConnectToBlock(level.getBlockState(pos.south()))) {
            currentState = currentState.setValue(SOUTH, true);
        }
        else{
            currentState = currentState.setValue(SOUTH, false);
        }
        if (canConnectToBlock(level.getBlockState(pos.west()))) {
            currentState = currentState.setValue(WEST, true);
        }
        else{
            currentState = currentState.setValue(WEST, false);
        }
        if (canConnectToBlock(level.getBlockState(pos.above()))) {
            currentState = currentState.setValue(UP, true);
        }
        else{
            currentState = currentState.setValue(UP, false);
        }
        if (canConnectToBlock(level.getBlockState(pos.below()))) {
            currentState = currentState.setValue(DOWN, true);
        }
        else{
            currentState = currentState.setValue(DOWN, false);
        }

        return currentState;
    }


    public @NotNull BlockState updateShape(@NotNull BlockState state, @NotNull Direction direction, @NotNull BlockState state2, @NotNull LevelAccessor level, @NotNull BlockPos pos, @NotNull BlockPos pos2){

        return getState(state, level, pos);
    }


    public BlockState getStateForPlacement(BlockPlaceContext context){

        return getState(defaultBlockState(), context.getLevel(), context.getClickedPos());

    }

    public @NotNull VoxelShape getShape(BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull CollisionContext context){

        VoxelShape shape = CENTER_SHAPE;
        if(state.getValue(NORTH)){
            shape = Shapes.or(shape, NORTH_SHAPE);
        }
        if(state.getValue(EAST)){
            shape = Shapes.or(shape, EAST_SHAPE);
        }
        if(state.getValue(SOUTH)){
            shape = Shapes.or(shape, SOUTH_SHAPE);
        }
        if(state.getValue(WEST)){
            shape = Shapes.or(shape, WEST_SHAPE);
        }
        if(state.getValue(UP)){
            shape = Shapes.or(shape, UP_SHAPE);
        }
        if(state.getValue(DOWN)){
            shape = Shapes.or(shape, DOWN_SHAPE);
        }

        return shape;
    }


}
