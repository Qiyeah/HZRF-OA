package com.oa.controller;

import com.base.interception.AssociationInterceptor;
import com.jfinal.aop.Before;
import com.zhilian.controller.BaseController;

@Before(AssociationInterceptor.class)
public class BaseAssociationController extends BaseController {
}