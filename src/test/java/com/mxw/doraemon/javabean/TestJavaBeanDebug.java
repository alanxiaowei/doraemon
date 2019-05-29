package com.mxw.doraemon.javabean;

import com.mxw.doraemon.utils.BeanDebugger;
import org.apache.velocity.runtime.directive.Foreach;

import java.beans.PropertyDescriptor;

/**
 * @program: doraemon
 * @description: 测试
 * @author: AlanMa
 * @create: 2019-05-27 19:29
 */
public class TestJavaBeanDebug {

	public static void main(String[] args) {
		Member member = new Member();
		BeanDebugger.dump(member);
		PropertyDescriptor[] descriptors=BeanDebugger.getAvailablePropertyDescriptors(member);
		for (PropertyDescriptor descriptor:descriptors
		     ) {
			System.out.println(descriptor.getReadMethod());
			System.out.println(descriptor.getWriteMethod());
			System.out.println(descriptor.getPropertyType());
			System.out.println(descriptor.getPropertyEditorClass());
			System.out.println(descriptor.getShortDescription());
		}
	}
}
