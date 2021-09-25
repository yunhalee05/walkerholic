import axios from "axios"
import { CREATE_ACTIVITY_FAIL, CREATE_ACTIVITY_REQUEST, CREATE_ACTIVITY_SUCCESS, CREATE_USER_ACTIVITY_FAIL, CREATE_USER_ACTIVITY_REQUEST, CREATE_USER_ACTIVITY_SUCCESS, DELETE_ACTIVITY_FAIL, DELETE_ACTIVITY_REQUEST, DELETE_ACTIVITY_SUCCESS, DELETE_USER_ACTIVITY_FAIL, DELETE_USER_ACTIVITY_REQUEST, DELETE_USER_ACTIVITY_SUCCESS, EDIT_USER_ACTIVITY_FAIL, EDIT_USER_ACTIVITY_REQUEST, EDIT_USER_ACTIVITY_SUCCESS, GET_ACTIVITIES_FAIL, GET_ACTIVITIES_REQUEST, GET_ACTIVITIES_SUCCESS, GET_ACTIVITY_FAIL, GET_ACTIVITY_REQUEST, GET_ACTIVITY_SUCCESS, GET_USER_ACTIVITIES_FAIL, GET_USER_ACTIVITIES_REQUEST, GET_USER_ACTIVITIES_SUCCESS } from "../_constants/ActivityConstants"

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



export const getUserActivities = (page) =>async(dispatch, getState)=>{

    const {auth :{user}} = getState()

    dispatch({
        type:GET_USER_ACTIVITIES_REQUEST
    })

    try{
        const res = await axios.get(`/userActivities/${page}/${user.id}`)

        dispatch({
            type:GET_USER_ACTIVITIES_SUCCESS,
            payload:res.data
        })

    }catch(error){
        dispatch({
            type:GET_USER_ACTIVITIES_FAIL,
            payload:error.response.data
            
        })
    }
}

export const createUserActivity = ({userActivityDTO}) =>async(dispatch, getState)=>{

    const {auth :{user}} = getState()

    dispatch({
        type:CREATE_USER_ACTIVITY_REQUEST
    })

    try{
        const res = await axios.post(`/userActivity/save/${user.id}`, userActivityDTO)

        dispatch({
            type:CREATE_USER_ACTIVITY_SUCCESS,
            payload:res.data.activity
        })

        return res.data.level

    }catch(error){
        dispatch({
            type:CREATE_USER_ACTIVITY_FAIL,
            payload:error.response.data
            
        })
    }
}

export const editUserActivity = ({userActivityDTO}) =>async(dispatch, getState)=>{

    const {auth :{user}} = getState()

    dispatch({
        type:EDIT_USER_ACTIVITY_REQUEST
    })

    try{
        const res = await axios.post(`/userActivity/save/${user.id}`, userActivityDTO)

        dispatch({
            type:EDIT_USER_ACTIVITY_SUCCESS,
            payload:res.data.activity
        })

        return res.data.level

    }catch(error){
        dispatch({
            type:EDIT_USER_ACTIVITY_FAIL,
            payload:error.response.data
            
        })
    }
}

export const deleteUserActivity = (id, score, finished) =>async(dispatch, getState)=>{

    const {auth :{user}} = getState()

    dispatch({
        type:DELETE_USER_ACTIVITY_REQUEST
    })

    try{
        const res = await axios.delete(`/userActivity/delete/${id}/${user.id}`)

        dispatch({
            type:DELETE_USER_ACTIVITY_SUCCESS,
            payload:{
                id,score,finished
            }
        })

        return res.data

    }catch(error){
        dispatch({
            type:DELETE_USER_ACTIVITY_FAIL,
            payload:error.response.data
            
        })
    }
}
