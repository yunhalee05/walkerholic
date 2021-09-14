import axios from "axios"
import { EDIT_PROFILE_FAIL, EDIT_PROFILE_REQUEST, EDIT_PROFILE_SUCCESS, GET_PROFILE_FAIL, GET_PROFILE_REQUEST, GET_PROFILE_SUCCESS } from "../_constants/ProfileConstants"

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


export const editProfile = (bodyFormData) =>async(dispatch, getState)=>{

    dispatch({
        type:EDIT_PROFILE_REQUEST
    })

    try{
        const res = await axios.post(`/user/save`,bodyFormData)

        dispatch({
            type:EDIT_PROFILE_SUCCESS,
            payload:res.data
        })

        return res.data.id

    }catch(error){
        dispatch({
            type:EDIT_PROFILE_FAIL,
            payload:error.response.data
            
        })
    }
}

