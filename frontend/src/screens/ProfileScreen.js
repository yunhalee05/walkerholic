import React from 'react'
import basicProfile from '../images/basicProfile.svg'

function ProfileScreen(props) {

    const id = props.match.params.id

    return (
        <div>
            Earth saver
            <div className="profile_image">
                <img src={basicProfile} alt="" style={{width:'50px'}}/>
            </div>
            <div className="profile_container">
                <div className="profile_content_container">
                    <div className="profile_content">
                        sldkfj
                    </div>
                    <div className="profile_content">
                        sldkfj
                    </div>
                    <div className="profile_content">
                        sldkfj
                    </div>
                    <div className="profile_content">
                        sldkfj
                    </div>
                </div>

                <div className="profile_button_container">
                    <div className="profile_follow_button">
                        <button>Follow</button>
                        <button>Unfollow</button>
                    </div>
                    <div className="profile_edit_button">
                        <button>Edit profile</button>
                    </div>
                </div>
            </div>

            
        </div>
    )
}

export default ProfileScreen
