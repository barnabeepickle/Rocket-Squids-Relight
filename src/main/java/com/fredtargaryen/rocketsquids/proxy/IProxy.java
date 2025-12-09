package com.fredtargaryen.rocketsquids.proxy;

import net.minecraft.client.model.HumanoidModel;

public interface IProxy
{
    void registerRenderers();

    void registerRenderTypes();

    void openConchClient(byte conchStage);

    HumanoidModel getConchModel();

    void playNoteFromMessage(byte note);

    void playNoteFromMessageConchNeeded(byte note);
}
