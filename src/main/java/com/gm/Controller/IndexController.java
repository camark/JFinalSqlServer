package com.gm.Controller;

import com.gm.Model.Jia;
import com.jfinal.core.Controller;

import java.util.List;

/**
 * Created by Administrator on 2017-01-12.
 */
public class IndexController extends Controller {
    public void index(){


        List<Jia> jias= Jia.dao.find("select top 10 cDepName,cPerName,iDays,iHours,cHdCode,cMemo from co_times order by autoid desc");
        setAttr("jias",jias);

        render("index.html");
    }
}
