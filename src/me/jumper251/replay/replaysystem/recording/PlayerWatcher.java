package me.jumper251.replay.replaysystem.recording;

import java.io.Serializable;



import com.comphenix.protocol.wrappers.WrappedDataWatcher;

import me.jumper251.replay.replaysystem.utils.MetadataBuilder;
import me.jumper251.replay.utils.VersionUtil;
import me.jumper251.replay.utils.VersionUtil.VersionEnum;

public class PlayerWatcher implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5198365909032922108L;
	
	
	private boolean sneaking, burning, blocking, elytra;
	
	private String name;
	
	public PlayerWatcher(String name) {
		this.sneaking = false;
		this.burning = false;
		this.blocking = false;
		this.elytra = false;
		this.name = name;
	}
	
	public WrappedDataWatcher getMetadata(MetadataBuilder builder) {
		
		if (isValueActive()) {
			byte sneakByte = (byte) (this.sneaking ? 0x02 : 0);
			byte burnByte = (byte) (this.burning ? 0x01 : 0);

			byte value = (byte) (burnByte | sneakByte);
			
			builder.setValue(0, value);
			
		} else {
			builder.resetValue();
		}
		
		byte blockByte = (byte) (this.blocking ? 0x01 : 0);

		if (VersionUtil.isCompatible(VersionEnum.V1_12)) {
			builder.setValue(6, blockByte);
		} else {
			builder.setValue(5, blockByte);
		}
		

		return builder.getData();
	}
	
	private boolean isValueActive() {
		return this.sneaking || this.blocking || this.burning || this.elytra;
	}
	
	public void setSneaking(boolean sneaking) {
		this.sneaking = sneaking;
	}
	
	public void setBlocking(boolean blocking) {
		this.blocking = blocking;
	}
	
	public void setBurning(boolean burning) {
		this.burning = burning;
	}
	
	public void setElytra(boolean elytra) {
		this.elytra = elytra;
	}
	
	public boolean isBurning() {
		return burning;
	}
	
	public boolean isBlocking() {
		return blocking;
	}
	
	public boolean isElytra() {
		return elytra;
	}
	
	public boolean isSneaking() {
		return sneaking;
	}
	
	public String getName() {
		return name;
	}
	
}