import axios from "axios"
import { CREATE_POST_FAIL, CREATE_POST_REQUEST, CREATE_POST_SUCCESS, GET_DISCOVER_POSTS_FAIL, GET_DISCOVER_POSTS_REQUEST, GET_DISCOVER_POSTS_SUCCESS, GET_FOLLOWINGS_POSTS_FAIL, GET_FOLLOWINGS_POSTS_REQUEST, GET_FOLLOWINGS_POSTS_SUCCESS } from "../_constants/PostConstants"

export const getDiscoverPosts = (page, id) =>async(dispatch, getState)=>{

    dispatch({
        type:GET_DISCOVER_POSTS_REQUEST
    })

    try{
        const res = await axios.get(`/posts/discover/${page}/${id}`)


        dispatch({
            type:GET_DISCOVER_POSTS_SUCCESS,
            payload:res.data
        })


    }catch(error){
        dispatch({
            type:GET_DISCOVER_POSTS_FAIL,
            payload:error.response.data
            
        })
    }
}
export const getFollowingsPosts = (page, id) =>async(dispatch, getState)=>{

    dispatch({
        type:GET_FOLLOWINGS_POSTS_REQUEST
    })

    try{
        const res = await axios.get(`/posts/follow/${page}/${id}`)


        dispatch({
            type:GET_FOLLOWINGS_POSTS_SUCCESS,
            payload:res.data
        })


    }catch(error){
        dispatch({
            type:GET_FOLLOWINGS_POSTS_FAIL,
            payload:error.response.data
            
        })
    }
}

export const createPost = (bodyFormData) =>async(dispatch, getState)=>{

    dispatch({
        type:CREATE_POST_REQUEST
    })

    try{
        const res = await axios.post('/post/save', bodyFormData)

        console.log(res)
        dispatch({
            type:CREATE_POST_SUCCESS,
            payload:res.data
        })


    }catch(error){
        dispatch({
            type:CREATE_POST_FAIL,
            payload:error.response.data
            
        })
    }
}

