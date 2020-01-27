package com.app.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.IUserDao;
import com.app.pojos.User;


@Service
@Transactional
public class IUserOtpImpl implements IUserOtp
{
	@Autowired
	IUserDao Iuserdao;

	
	@Override
	public int generatorOTP()
	{
		Random random=new Random();
		
		int num=random.nextInt(99999)+99999;
		
		if(num<100000 || num>999999)
		{
			num=random.nextInt(99999)+99999;
			
			if(num<100000 || num>999999)
			{
				System.out.println("not generated otp");
			}
		}
		return num;
	}

	
	@Override
	public void setPassword(String getuPassword, String getuEmail) 
	{
		System.out.println(getuPassword+""+getuEmail);
		
	  	Iuserdao.setPass(getuEmail, getuPassword);
		
	}

	

}
