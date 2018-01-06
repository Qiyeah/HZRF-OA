package com.oa.model.work;

import com.jfinal.plugin.activerecord.Model;

public class T_Sound extends Model<T_Sound> {

    private static final long serialVersionUID = 5820143421755087772L;
    public static final T_Sound dao = new T_Sound();

    public T_Sound findByUserId(int userId) {
	return dao.findFirst("SELECT * FROM t_sound where user_id = " + userId);
    }
}
