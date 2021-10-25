import axios from "axios"
import { CREATE_POST_FAIL, CREATE_POST_REQUEST, CREATE_POST_SUCCESS, DELETE_POST_FAIL, DELETE_POST_REQUEST, DELETE_POST_SUCCESS, GET_DISCOVER_POSTS_FAIL, GET_DISCOVER_POSTS_REQUEST, GET_DISCOVER_POSTS_SUCCESS, GET_FOLLOWINGS_POSTS_FAIL, GET_FOLLOWINGS_POSTS_REQUEST, GET_FOLLOWINGS_POSTS_SUCCESS, GET_HOME_POST_FAIL, GET_HOME_POST_REQUEST, GET_HOME_POST_SUCCESS, GET_POST_FAIL, GET_POST_REQUEST, GET_POST_SUCCESS, GET_SEARCH_POST_FAIL, GET_SEARCH_POST_REQUEST, GET_SEARCH_POST_SUCCESS, LIKE_POST_FAIL, LIKE_POST_REQUEST, LIKE_POST_SUCCESS, UNLIKE_POST_FAIL, UNLIKE_POST_REQUEST, UNLIKE_POST_SUCCESS } from "../_constants/PostConstants"

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
            payload:{
                posts:res.data.posts,
                totalElement:res.data.totalElement,
                totalPage:res.data.totalPage,
                page:page
            }
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

export const deletePost = (id) =>async(dispatch, getState)=>{

    dispatch({
        type:DELETE_POST_REQUEST
    })

    try{
        await axios.delete(`/post/${id}`)

        dispatch({
            type:DELETE_POST_SUCCESS,
            payload:id
        })


    }catch(error){
        dispatch({
            type:DELETE_POST_FAIL,
            payload:error.response.data
            
        })
    }
}

export const getPost = (id) =>async(dispatch, getState)=>{

    dispatch({
        type:GET_POST_REQUEST
    })

    try{
        const res = await axios.get(`/post/${id}`)

        dispatch({
            type:GET_POST_SUCCESS,
            payload:res.data
        })


    }catch(error){
        dispatch({
            type:GET_POST_FAIL,
            payload:error.response.data
            
        })
    }
}

export const getHomePost = (page, sort) =>async(dispatch, getState)=>{

    dispatch({
        type:GET_HOME_POST_REQUEST
    })

    try{
        const res = await axios.get(`/posts/home/${page}/${sort}`)

        dispatch({
            type:GET_HOME_POST_SUCCESS,
            payload:{
                posts:res.data.posts,
                totalElement:res.data.totalElement,
                totalPage:res.data.totalPage,
                page:page
            }
        })
        return res.data.posts

    }catch(error){
        dispatch({
            type:GET_HOME_POST_FAIL,
            payload:error.response.data
            
        })
    }
}
export const likePost = (postId) =>async(dispatch, getState)=>{

    const {auth: {user}} = getState()

    dispatch({
        type:LIKE_POST_REQUEST
    })

    try{
        const res = await axios.post(`/likePost/${postId}/${user.id}`, null)
        console.log(res)

        const likePost = {id:res.data.id, userId:res.data.user.id, fullname:res.data.user.fullname, imageUrl:res.data.user.imageUrl}

        
        dispatch({
            type:LIKE_POST_SUCCESS,
            payload:{
                postId:postId,
                likePost:likePost
            }
        })


    }catch(error){
        dispatch({
            type:LIKE_POST_FAIL,
            payload:error.response.data
            
        })
    }
}

export const unlikePost = (postId, id) =>async(dispatch, getState)=>{

    const {auth: {user}} = getState()

    dispatch({
        type:UNLIKE_POST_REQUEST
    })

    try{
        const res = await axios.delete(`/unlikePost/${id}`)
        console.log(res)

        dispatch({
            type:UNLIKE_POST_SUCCESS,
            payload:{
                likeId:res.data,
                postId:postId
            }
        })


    }catch(error){
        dispatch({
            type:UNLIKE_POST_FAIL,
            payload:error.response.data
            
        })
    }
}

export const getSearchPosts = (page, sort, keyword) =>async(dispatch, getState)=>{

    dispatch({
        type:GET_SEARCH_POST_REQUEST
    })

    try{
        const res = await axios.get(`/posts/search/${page}/${sort}/${keyword}`)

        dispatch({
            type:GET_SEARCH_POST_SUCCESS,
            payload:{
                posts:res.data.posts,
                totalElement:res.data.totalElement,
                totalPage:res.data.totalPage,
                page:page
            }
        })
        console.log(res.data)

    }catch(error){
        dispatch({
            type:GET_SEARCH_POST_FAIL,
            payload:error.response.data
            
        })
    }
}