package com.valkryst.VRoguelike.ai.movement;

import com.valkryst.VRoguelike.world.Map;
import lombok.NonNull;

import java.awt.Point;
import java.util.ArrayDeque;

public class AStarMovementAI extends MovementAI {
    @Override
    public ArrayDeque<Point> findPath(final @NonNull Map map, final @NonNull Point start, final @NonNull Point end) {
        return new ArrayDeque<>();
    }
}
