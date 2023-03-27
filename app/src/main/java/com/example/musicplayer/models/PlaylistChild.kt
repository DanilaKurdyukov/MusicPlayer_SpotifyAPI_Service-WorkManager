package com.example.musicplayer.models

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

class PlaylistChild(@SerializedName("id") var playlistId: String?, var name: String?, var images: List<Image>, var type: String?,
                    var tracks: PlaylistTrack, @SerializedName("owner")
                    var playlistOwner: PlaylistOwner, @SerializedName("collaborative") var isPlaylistCollaborative: Boolean) : Parcelable {
        constructor(parcel: Parcel) : this(
                parcel.readString(),
                parcel.readString(),
                TODO("images"),
                parcel.readString(),
                TODO("tracks"),
                TODO("playlistOwner"),
                parcel.readByte() != 0.toByte()
        ) {
        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
                parcel.writeString(playlistId)
                parcel.writeString(name)
                parcel.writeString(type)
                parcel.writeByte(if (isPlaylistCollaborative) 1 else 0)
        }

        override fun describeContents(): Int {
                return 0
        }

        companion object CREATOR : Parcelable.Creator<PlaylistChild> {
                override fun createFromParcel(parcel: Parcel): PlaylistChild {
                        return PlaylistChild(parcel)
                }

                override fun newArray(size: Int): Array<PlaylistChild?> {
                        return arrayOfNulls(size)
                }
        }
}