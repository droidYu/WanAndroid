package com.droidyu.wanandroid.ui.home

import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.droidyu.wanandroid.data.entity.BannerImg
import com.youth.banner.adapter.BannerImageAdapter
import com.youth.banner.holder.BannerImageHolder

class BannerImgAdapter(imgs: List<BannerImg>) : BannerImageAdapter<BannerImg>(imgs) {
    override fun onBindView(
        holder: BannerImageHolder,
        data: BannerImg,
        position: Int,
        size: Int
    ) {
        Glide.with(holder.itemView)
            .load(data.imagePath)
            .apply(RequestOptions.bitmapTransform(RoundedCorners(30)))
            .into(holder.imageView)
    }
}