package db;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.imageio.ImageIO;

import com.mysql.cj.jdbc.Blob;

import application.User;

public class UserDatabase implements Database<User>{
	
	private static PreparedStatement stmt = null;
	
	public UserDatabase() {}

	@Override
	public void insert(User user) {
		String query = "INSERT INTO user(user_id, login, pass, email, imagem) "
					 + "VALUES(?,?,?,?,?)";
		
		try (Connection conn = ConnectionFactory.getConnetion()){
			BufferedImage bi = ImageIO.read(new File("C:\\Repositorios\\IA-Learning-to-Drive\\agoravai\\media\\pista.png"));
			ByteArrayOutputStream bs = new ByteArrayOutputStream();
			ImageIO.write(bi, "png", bs);
			byte[] blob = bs.toByteArray();
			
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, user.getId());
			stmt.setString(2, user.getLogin());
			stmt.setString(3, user.getPassword());
			stmt.setString(4, user.getEmail());
			stmt.setBytes(5, blob);
			stmt.execute();
		} catch (SQLException | IOException ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void update(User user) {
		//TODO preciso saber quais campos vão poder ser alterados.
	}

	@Override
	public void delete(Integer id) {
		String query = "DELETE user "
					 + "WHERE user_id = ?";
		
		try(Connection conn = ConnectionFactory.getConnetion()){
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, id);
			stmt.execute();
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * Função auxiliar para saber qual o ultimo id inserido na tabela.
	 */
	public static Integer getId() {
		String query = "SELECT MAX(user_id)+1 FROM user";
		Integer maxId = 0;
		
		try(Connection conn = ConnectionFactory.getConnetion()){
			stmt = conn.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			maxId = rs.getInt(1);
		} catch(SQLException ex){
			ex.printStackTrace();
		}
		return maxId;
	}
	
	public static BufferedImage getImageTrack(Integer userId) {
		String query = "SELECT imagem FROM user WHERE user_id = ?";
		BufferedImage bi = null;
		Blob blob = null;
		try(Connection conn = ConnectionFactory.getConnetion()){
			stmt = conn.prepareStatement(query);
			stmt.setInt(1,userId);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			blob = (Blob) rs.getBlob(1);
			InputStream in = blob.getBinaryStream();
			bi = ImageIO.read(in);
			
		}catch(SQLException | IOException ex) {
			ex.printStackTrace();
		}
		return bi;
	}
	
	public static User getUser(String login, String pass) {
		User user = null;
		String query = "SELECT user_id, login, pass, email FROM user WHERE login = ? AND pass = ?";
		try(Connection conn = ConnectionFactory.getConnetion()){
			stmt = conn.prepareStatement(query);
			stmt.setString(1,login);
			stmt.setString(2,pass);
			
			ResultSet rs = stmt.executeQuery();
			rs.next();
			user = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		return user;
	}
}
