import axios from "axios"
import { GET_PROFILE_FAIL, GET_PROFILE_REQUEST, GET_PROFILE_SUCCESS } from "../_constants/ProfileConstants"

export const getProfile = (id) =>async(dispatch, getState)=>{

    dispatch({
        type:GET_PROFILE_REQUEST
    })

    try{
        const res1 = await axios.get(`/user/${id}`)
        const res2 = await axios.get(`/follows/${id}`)
        const res3 = await axios.get(`/posts/${id}`)


        dispatch({
            type:GET_PROFILE_SUCCESS,
            payload:{
                user:res1.data,
                followers:res2.data.followers,
                followings:res2.data.followings,
                posts:res3.data.posts,
                likePosts:res3.data.likePosts
            }
        })


    }catch(error){
        dispatch({
            type:GET_PROFILE_FAIL,
            payload:error.response.data
            
        })
    }
}

