package com.example.londonapp.domain

import androidx.annotation.DrawableRes

internal sealed class GetPreviousPictureResult{

    data class Success(
        @DrawableRes val previousPic: Int,
        val isFirstPicture: Boolean,
    ) : GetPreviousPictureResult()

    data object Error : GetPreviousPictureResult()

}

internal interface IGetPreviousPictureUseCase {

    operator fun invoke(placeId: Int, @DrawableRes currentPic: Int): GetPreviousPictureResult

}

internal class GetPreviousPictureUseCase(
    private val getPlaceByIdUseCase: IGetPlaceByIdUseCase
) : IGetPreviousPictureUseCase {

    override fun invoke(placeId: Int, @DrawableRes currentPic: Int): GetPreviousPictureResult {
        val pics = getPlaceByIdUseCase.invoke(placeId).pictureReferences
        if (pics[0] != currentPic) {
            val indexOfPreviousPic = pics.indexOf(currentPic) - 1
            return GetPreviousPictureResult.Success(
                previousPic = pics[indexOfPreviousPic],
                isFirstPicture = indexOfPreviousPic == 0
            )
        } else {
            return GetPreviousPictureResult.Error
        }
    }

}
