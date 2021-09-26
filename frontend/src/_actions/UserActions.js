import axios from "axios"
import { DELETE_USER_FAIL, DELETE_USER_REQUEST, DELETE_USER_SUCCESS, GET_USER_LIST_FAIL, GET_USER_LIST_REQUEST, GET_USER_LIST_SUCCESS } from "../_constants/UserConstants"

export const getUserList = (page, sort) =>async(dispatch, getState)=>{

    dispatch({
        type:GET_USER_LIST_REQUEST
    })

    try{
        const res = await axios.get(`/userlist/${page}/${sort}`)

        dispatch({
            type:GET_USER_LIST_SUCCESS,
            payload:res.data
        })

        return res.data.id

    }catch(error){
        dispatch({
            type:GET_USER_LIST_FAIL,
            payload:error.response.data
            
        })
    }
}

export const deleteUser = (id) =>async(dispatch, getState)=>{

    dispatch({
        type:DELETE_USER_REQUEST
    })

    try{
        const res = await axios.delete(`/user/delete/${id}`)

        dispatch({
            type:DELETE_USER_SUCCESS,
            payload:res.data
        })

        return res.data

    }catch(error){
        dispatch({
            type:DELETE_USER_FAIL,
            payload:error.response.data
            
        })
    }
}

