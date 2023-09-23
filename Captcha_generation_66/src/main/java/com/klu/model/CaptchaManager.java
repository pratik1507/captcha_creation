package com.klu.model;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Random;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klu.entity.CaptchaEntity;
import com.klu.repository.CaptchaRepository;

@Service
public class CaptchaManager {
	@Autowired
	CaptchaRepository cr;
	public String randomText(int clength) {
		String ctext="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		StringBuffer sb=new StringBuffer();
		Random rm=new Random();
		while(sb.length()<clength) {
			int index=(int)(rm.nextFloat()*ctext.length());
			String c=ctext.substring(index,index+1);
			sb.append(c);
		}
		return sb.toString();
	}
	public byte[] getCaptcha(String txt) {
		Long id=(long)1;
		cr.deleteAll();
		CaptchaEntity ce=new CaptchaEntity();
		ce.setId(id);
		ce.setCaptchatext(txt);
		cr.save(ce);
		int width=160,height=40;
		BufferedImage img=new BufferedImage(width, height, BufferedImage.OPAQUE);
		Graphics g=img.createGraphics();
		g.setFont(new Font("Arial", Font.BOLD, 20));
		g.setColor(Color.CYAN);
		g.fillRect(0, 0, width, height);
		g.setColor(Color.WHITE);
		g.drawString(txt,20, 25);
		ByteArrayOutputStream bout=new ByteArrayOutputStream();
		try 
		{
		  ImageIO.write(img,"png", bout);
		}
		catch(Exception e) 
		{
		   new RuntimeException(e);
		}
		
	   return bout.toByteArray();
	}
	
	public String validateCaptcha(String txt)
	 {
	  Long id = (long) 1;
	  CaptchaEntity tmp = cr.findById(id).get();
	  if(txt.compareTo(tmp.getCaptchatext())==0)
	   return "Validation Success";
	  
	  return "Validation Failed";
	 }
}
