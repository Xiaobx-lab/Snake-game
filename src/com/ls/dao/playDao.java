package com.ls.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.ls.bean.Player;
import com.ls.bean.Result;
import com.ls.controller.MainController;

import pool.PoolUtil;

public class playDao {

	public Player login(Player p) {
		//1:qr
		QueryRunner qr = new QueryRunner(PoolUtil.getDatasource());
		//2:sql
		String sql = "select * from player where pname = ? and ppass = ?";
		//3:?
		Object[]params = {p.getPname(),p.getPass()};
		//4:执行查询 封装结果返回
		try {
			Player player = qr.query(sql, new BeanHandler<Player>(Player.class),params);
			return player;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("playerDao"+p+"出现问题");
		}
	}

	public int register(Player player) {
		QueryRunner qr = new QueryRunner(PoolUtil.getDatasource());
		String sql = "insert into player (pname,ppass,pnick)values(?,?,?)";
		Object []params = {player.getPname(),player.getPass(),player.getPnick()};
		try {
			int rows = qr.execute(sql, params);
			return rows;
		} catch (SQLException e) {
		//	e.printStackTrace();
			int error = 1045;
			return error;
		}
	}

	public Player searchID(Player p) {
		QueryRunner qr = new QueryRunner(PoolUtil.getDatasource());
		String sql = "select * from player where pname =  ?";
		Object[]params = {p.getPname()};
		try {
			Player player = qr.query(sql, new BeanHandler<Player>(Player.class),params);
			return player;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("playerDao查询出现问题了！！！");
		}
	}
	

	public int addPoint(Result result) {
		QueryRunner qr = new QueryRunner(PoolUtil.getDatasource());
		String sql = "insert into record(rscore,rtime,pid)values(?,?,?)";
		String date = MainController.getCon().getNowDay();
		String score = (MainController.getCon().getSnake().getLen()*3)+"";
		Object params[] = {score,date,result.getPid()};
		try {
			int rows = qr.execute(sql, params);
			return rows;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("playerDao新增分数数据出现问题了！");
		}
	}



}
