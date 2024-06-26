package reborncore.common.packets;

import java.io.IOException;
import java.util.EnumMap;
import java.util.logging.Logger;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.Packet;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

import cpw.mods.fml.common.network.FMLEmbeddedChannel;
import cpw.mods.fml.common.network.FMLIndexedMessageToMessageCodec;
import cpw.mods.fml.common.network.FMLOutboundHandler;
import cpw.mods.fml.relauncher.Side;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

public class PacketHandler extends FMLIndexedMessageToMessageCodec<SimplePacket> {

    private static EnumMap<Side, FMLEmbeddedChannel> channels;
    public int nextDiscriminator = 0;

    public PacketHandler() {
        MinecraftForge.EVENT_BUS.post(new AddDiscriminatorEvent(this));
    }

    public static EnumMap<Side, FMLEmbeddedChannel> getChannels() {
        return channels;
    }

    public static void setChannels(EnumMap<Side, FMLEmbeddedChannel> _channels) {
        channels = _channels;
    }

    public static void sendPacketToServer(SimplePacket packet) {
        PacketHandler.getChannels().get(Side.CLIENT).attr(FMLOutboundHandler.FML_MESSAGETARGET)
                .set(FMLOutboundHandler.OutboundTarget.TOSERVER);
        PacketHandler.getChannels().get(Side.CLIENT).writeOutbound(packet);
    }

    public static void sendPacketToPlayer(SimplePacket packet, EntityPlayer player) {
        PacketHandler.getChannels().get(Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGET)
                .set(FMLOutboundHandler.OutboundTarget.PLAYER);
        PacketHandler.getChannels().get(Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGETARGS).set(player);
        PacketHandler.getChannels().get(Side.SERVER).writeOutbound(packet);
    }

    public static void sendPacketToAllPlayers(SimplePacket packet) {
        PacketHandler.getChannels().get(Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGET)
                .set(FMLOutboundHandler.OutboundTarget.ALL);
        PacketHandler.getChannels().get(Side.SERVER).writeOutbound(packet);
    }

    public static void sendPacketToAllPlayers(Packet packet, World world) {
        for (Object player : world.playerEntities) {
            if (player instanceof EntityPlayerMP)
                if (player != null) ((EntityPlayerMP) player).playerNetServerHandler.sendPacket(packet);
        }
    }

    @Override
    public void encodeInto(ChannelHandlerContext ctx, SimplePacket msg, ByteBuf target) throws Exception {
        msg.writePacketData(target);
    }

    @Override
    public void decodeInto(ChannelHandlerContext ctx, ByteBuf source, SimplePacket msg) {
        try {
            msg.readPacketData(source);
            msg.execute();
        } catch (IOException e) {
            Logger.getLogger("Network").warning("Something caused a Protocol Exception!");
        }
    }

    @Override
    public FMLIndexedMessageToMessageCodec<SimplePacket> addDiscriminator(int discriminator,
            Class<? extends SimplePacket> type) {
        nextDiscriminator++;
        return super.addDiscriminator(discriminator, type);
    }
}
