package com.nuc.bean;

import java.util.Date;

public class SongsBean {
	private int songId;
	private String songName;
	private int singerId;
	private Date songTime;
	private String songWord;
	private int songHeat;
	private int typeId;
	private String songAlbum;
	private String songImg;
	
	public String getSongAlbum() {
		return songAlbum;
	}
	public void setSongAlbum(String songAlbum) {
		this.songAlbum = songAlbum;
	}
	public String getSongImg() {
		return songImg;
	}
	public void setSongImg(String songImg) {
		this.songImg = songImg;
	}
	public int getTypeId() {
		return typeId;
	}
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
	public int getSongId() {
		return songId;
	}
	public void setSongId(int songId) {
		this.songId = songId;
	}
	public String getSongName() {
		return songName;
	}
	public void setSongName(String songName) {
		this.songName = songName;
	}
	public int getSingerId() {
		return singerId;
	}
	public void setSingerId(int singerId) {
		this.singerId = singerId;
	}
	public Date getSongTime() {
		return songTime;
	}
	public void setSongTime(Date songTime) {
		this.songTime = songTime;
	}
	public String getSongWord() {
		return songWord;
	}
	public void setSongWord(String songWord) {
		this.songWord = songWord;
	}
	public int getSongHeat() {
		return songHeat;
	}
	public void setSongHeat(int songHeat) {
		this.songHeat = songHeat;
	}
	
}
