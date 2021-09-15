import axios from "axios"
import { FOLLOW_FAIL, FOLLOW_REQUEST, FOLLOW_SUCCESS, UNFOLLOW_FAIL, UNFOLLOW_REQUEST, UNFOLLOW_SUCCESS } from "../_constants/FollowConstants"

export const follow = (id) =>async(dispatch, getState)=>{

    const {auth : {user}} = getState()

    dispatch({
        type:FOLLOW_REQUEST
    })

    try{
        const res = await axios.post(`/follow/${user.id}/${id}`,null)

        const followed = {id:res.data.id, user:{id:user.id, fullname:user.firstname+user.lastname,imageUrl:user.imageUrl }}
        console.log(res)
        dispatch({
            type:FOLLOW_SUCCESS,
            payload:{
                follow:res.data,
                id:user.id,
                followed: followed
            }
        })


    }catch(error){
        dispatch({
            type:FOLLOW_FAIL,
            payload:error.response.data
            
        })
    }
}

export const unfollow = (id) =>async(dispatch, getState)=>{

    dispatch({
        type:UNFOLLOW_REQUEST
    })

    try{
        await axios.delete(`/unfollow/${id}`)

        dispatch({
            type:UNFOLLOW_SUCCESS,
            payload:id
        })


    }catch(error){
        dispatch({
            type:UNFOLLOW_FAIL,
            payload:error.response.data
            
        })
    }
}
