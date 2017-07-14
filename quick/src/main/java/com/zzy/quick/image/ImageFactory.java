package com.zzy.quick.image;

/**
 * 项目名称: QuickMvp
 * 创建人: 周正一
 * 创建时间：2017/7/13
 * 图片加载的门面，使用者无需关心是使用Glide加载图片还是Picasso，以后切换图片框架，在这里更改加载器就行了，这一切对使用者
 * 都是透明的。
 */

public class ImageFactory {

    private static ImageLoader loader;

    public static ImageLoader getImageLoader(){
        if(loader==null){
            synchronized (ImageFactory.class){
                if(loader==null){
                    loader=new GlideLoader();
                }
            }
        }
        return loader;
    }
}
