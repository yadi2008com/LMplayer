package com.nuc.biz;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.nuc.bean.SongsBean;
import com.nuc.bean.SongsVo;
import com.nuc.dao.SongDao;
import com.nuc.dao.SongDaoImpl;
import com.nuc.frameworkUtil.DataUtil;
import com.nuc.frameworkUtil.RequestUtil;

/**
 * 
 * @author yadi
 *
 */
public class SongBizImpl implements SongBiz {
	private SongDao songDao;
	/**
	 * @author yadi
	 * @todo 增加歌曲
	 * @param songParamter 歌曲内容信息
	 * @throws Exception if have a error
	 */
	@Override
	public SongsBean addBeforeSong(String...songParamter) throws Exception{
		if(!DataUtil.mustValidateString(songParamter)){
			throw new Exception("添加歌曲参数异常！");
		}
		SongsBean songsBean=new SongsBean();
		songsBean.setSongName(songParamter[0]);
		songsBean.setTypeId(Integer.valueOf(songParamter[1]));
		
		songsBean.setSongTime(new Date());
		songsBean.setSingerId(Integer.valueOf(songParamter[2]));
		songsBean.setSongHeat(Integer.valueOf(songParamter[4]));
		songsBean.setSongWord(songParamter[3]);
		//songsBean.setSongAlbum(songParamter[5]);
		//songsBean.setSongImg(songParamter[6]);
		return songsBean;
	}
	
	/**
	 * @author yadi
	 * @param songsBean 歌曲bin
	 * @throws Exception if have a error
	 */
	@Override
	public void addSong(SongsBean songsBean,HttpServletRequest request) throws Exception{
		songDao=new SongDaoImpl();
		int songId=songDao.addSong(songsBean);
		try {
			 RequestUtil.writeSongFile(request, songId);
		} catch (Exception e) {
			e.printStackTrace();
			try {
				this.deleteSonnById(songId);
				throw new Exception("上传歌曲附件失败！！！！");
			} catch (Exception e1) {
				//删除歌曲
				e1.printStackTrace();
				throw new Exception("上传歌曲附件失败！！！！");
			}
		}
		
	}
	/**
	 * 删除歌曲内容
	 * @param id 歌曲信息编号
	 * @throws Exception if have a error
	 */
	@Override
	public void deleteSonnById(int id) throws Exception{
		songDao.deleteSonnById(id);
	}
	/**
	 * @function 得到歌曲列表行数*/
	@Override
	public int getSongInfoRows() {
		int result = 0;
		songDao=new SongDaoImpl();
		try {
			result = songDao.getSongInfoRows();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * @function 得到歌曲列表行数
	 * @param 歌曲名称*/
	@Override
	public int getSongInfoRows(String songName) {
		int result = 0;
		songDao=new SongDaoImpl();
		try {
			result = songDao.getSongInfoRows(songName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * @funtion 歌曲列表*/
	@Override
	public List<SongsVo> fetchAllSongsInfoList(int pageno){
		List<SongsVo> songsVoInfoList = null;
		try {
			songsVoInfoList = songDao.fetchAllSongsInfoList(pageno);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return songsVoInfoList;
	}
	/**
	 * @funtion 歌曲列表*/
	
	/**
	 * @funtion 歌曲列表
	 * @param songName*/
	@Override
	public List<SongsVo> fetchAllSongsInfoList(int pageno,String songName){
		List<SongsVo> songsVoInfoList = null;
		try {
			songsVoInfoList = songDao.fetchAllSongsInfoList(pageno,songName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return songsVoInfoList;
	}

	@Override
	public List<SongsVo> fetchAllSongsInfoList() {
		List<SongsVo> songsVoInfoList = null;
		try {
			songsVoInfoList = songDao.fetchAllSongsInfoList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return songsVoInfoList;
	}

	@Override
	public List<SongsVo> fetchAllSongsIdList() {
		List<SongsVo> songsVoInfoList = null;
		try {
			songsVoInfoList = songDao.fetchAllSongsIdList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return songsVoInfoList;
	}

	@Override
	public List<SongsBean> fetchAllSongForAnd() {
		// 创建堆内存空间并赋值为空
				List<SongsBean> songsInfoList = null;
				try {
					songsInfoList = songDao.fetchAllSongForAnd();
				} catch (Exception e) {
					e.printStackTrace();
				}
				return songsInfoList;
	}
	/**
	 * @funtcion 删除歌曲*/
	@Override
	public boolean deleteSongById(int songId) {
		boolean flag = false;
		try {
			flag = songDao.deleteSongById(songId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	/**
	 * @funtion 排行后歌曲列表*/
	@Override
	public List<SongsVo> fetchAllSongsRankInfoList(int pageno) {
		List<SongsVo> songsVoInfoList = null;
		try {
			songsVoInfoList = songDao.fetchAllSongsRankInfoList(pageno);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return songsVoInfoList;
	}
	
}
