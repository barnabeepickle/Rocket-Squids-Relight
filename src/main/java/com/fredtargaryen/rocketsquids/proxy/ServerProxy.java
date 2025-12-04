package com.fredtargaryen.rocketsquids.proxy;

import net.minecraft.client.model.HumanoidModel;

public class ServerProxy implements IProxy {
    @Override
    public void registerRenderers(){}

    @Override
    public void registerRenderTypes(){}

    @Override
    public void openConchClient(byte conchStage){}

    @Override
    public HumanoidModel getConchModel() { return null; }

    @Override
    public void playNoteFromMessage(byte note) {}

    @Override
    public void playNoteFromMessageConchNeeded(byte note) {}
}
