import axios from "axios"
import { CREATE_ACTIVITY_FAIL, CREATE_ACTIVITY_REQUEST, CREATE_ACTIVITY_SUCCESS, DELETE_ACTIVITY_FAIL, DELETE_ACTIVITY_REQUEST, DELETE_ACTIVITY_SUCCESS, GET_ACTIVITIES_FAIL, GET_ACTIVITIES_REQUEST, GET_ACTIVITIES_SUCCESS, GET_ACTIVITY_FAIL, GET_ACTIVITY_REQUEST, GET_ACTIVITY_SUCCESS } from "../_constants/ActivityConstants"

export const getActivities = () =>async(dispatch, getState)=>{

    dispatch({
        type:GET_ACTIVITIES_REQUEST
    })

    try{
        const res = await axios.get('/activities')

        dispatch({
            type:GET_ACTIVITIES_SUCCESS,
            payload:res.data
        })

    }catch(error){
        dispatch({
            type:GET_ACTIVITIES_FAIL,
            payload:error.response.data
            
        })
    }
}

export const getActivity = (id) =>async(dispatch, getState)=>{

    dispatch({
        type:GET_ACTIVITY_REQUEST
    })

    try{
        const res = await axios.get(`/activity/${id}`)
        console.log(res)

        dispatch({
            type:GET_ACTIVITY_SUCCESS,
            payload:res.data
        })

    }catch(error){
        dispatch({
            type:GET_ACTIVITY_FAIL,
            payload:error.response.data
            
        })
    }
}

export const saveActivity = (bodyFormData) =>async(dispatch, getState)=>{

    dispatch({
        type:CREATE_ACTIVITY_REQUEST
    })

    try{
        const res = await axios.post('/activity/save',bodyFormData)

        dispatch({
            type:CREATE_ACTIVITY_SUCCESS,
            payload:res.data
        })

    }catch(error){
        dispatch({
            type:CREATE_ACTIVITY_FAIL,
            payload:error.response.data
            
        })
    }
}

export const deleteActivity = (id) =>async(dispatch, getState)=>{

    dispatch({
        type:DELETE_ACTIVITY_REQUEST
    })

    try{
        const res = await axios.delete(`/activity/${id}`)

        dispatch({
            type:DELETE_ACTIVITY_SUCCESS,
            payload:id
        })

    }catch(error){
        dispatch({
            type:DELETE_ACTIVITY_FAIL,
            payload:error.response.data
            
        })
    }
}
