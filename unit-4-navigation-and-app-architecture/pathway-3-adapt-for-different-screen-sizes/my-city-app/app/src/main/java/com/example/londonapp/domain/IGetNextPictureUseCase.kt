package com.example.londonapp.domain

import androidx.annotation.DrawableRes

internal sealed class GetNextPictureResult{

    data class Success(
        @DrawableRes val nextPic: Int,
        val isLastPicture: Boolean,
    ) : GetNextPictureResult()

    data object Error : GetNextPictureResult()

}

internal interface IGetNextPictureUseCase {

    operator fun invoke(placeId: Int, @DrawableRes currentPic: Int): GetNextPictureResult

}

internal class GetNextPictureUseCase(
    private val getPlaceByIdUseCase: GetPlaceByIdUseCase
) : IGetNextPictureUseCase {

    override fun invoke(placeId: Int, @DrawableRes currentPic: Int): GetNextPictureResult {
        val pics = getPlaceByIdUseCase.invoke(placeId).pictureReferences
        if (pics.last() != currentPic) {
            val indexOfNextPic = pics.indexOf(currentPic) + 1
            return GetNextPictureResult.Success(
                nextPic = pics[indexOfNextPic],
                isLastPicture = indexOfNextPic == pics.size - 1
            )
        } else {
            return GetNextPictureResult.Error
        }
    }

}
