import React, { useEffect, useState } from 'react'
import { useDispatch, useSelector } from 'react-redux'
import EditProfile from '../components/profile/EditProfile'
import basicProfile from '../images/basicProfile.svg'
import { getProfile } from '../_actions/ProfileActions'

function ProfileScreen(props) {

    const id = props.match.params.id

    const [isEdit, setIsEdit] = useState(false)

    const dispatch = useDispatch()

    useEffect(() => {
        dispatch(getProfile(id))
    }, [dispatch, id])

    const auth = useSelector(state => state.auth)
    const profile = useSelector(state => state.profile)

    return (
        <div>
            Earth saver
            {
                (profile.loading ===false && profile.user.id) &&
                <div>
                    <div className="profile_image">
                        <img src={profile.user.imageUrl ? profile.user.imageUrl : basicProfile} alt="" style={{width:'50px'}}/>
                    </div>
                    <div className="profile_container">
                        <div className="profile_content_container">
                            <div className="profile_content">
                                {profile.user.firstname}&nbsp;{profile.user.lastname}
                            </div>
                            <div className="profile_content">
                                {profile.user.level}
                            </div>
                            <div className="profile_content">
                                {profile.user.email}
                            </div>
                            <div className="profile_content">
                                {profile.user.phoneNumber}
                            </div>
                            <div className="profile_content">
                                {profile.user.description}
                            </div>
                            <div className="profile_content">
                                {profile.followers.length}followers
                                {profile.followings.length} followings
                            </div>
                        </div>

                        <div className="profile_button_container">
                            {
                                auth.user.id !== profile.user.id
                                ?
                                    <div className="profile_follow_button">
                                        {
                                            profile.user.followers.filter(f=> f.user.id === auth.user.id).length >0
                                            ?<button>Unfollow</button>
                                            :<button>Follow</button>
                                        }
                                    </div>
                                :
                                <div className="profile_edit_button">
                                    <button onClick={()=>setIsEdit(!isEdit)}>Edit profile</button>
                                </div>
                            }
                        </div>
                    </div>

                    {
                        isEdit &&
                        <EditProfile setIsEdit={setIsEdit}/>
                    }
                </div>
            }
            
        </div>
    )
}

export default ProfileScreen
