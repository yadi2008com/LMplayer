package com.nuc.bean;

import java.util.Date;

public class SongsVo {
	private int songId;
	private String songName;
	private String singerName;
	private Date songTime;
	private String songWord;
	private int songHeat;
	private String typeName;
	private String songAlbum;
	
	public String getSongAlbum() {
		return songAlbum;
	}
	public void setSongAlbum(String songAlbum) {
		this.songAlbum = songAlbum;
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
	public String getSingerName() {
		return singerName;
	}
	public void setSingerName(String singerName) {
		this.singerName = singerName;
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
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

}
