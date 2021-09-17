import { CREATE_ACTIVITY_FAIL, CREATE_ACTIVITY_REQUEST, CREATE_ACTIVITY_SUCCESS, DELETE_ACTIVITY_FAIL, DELETE_ACTIVITY_REQUEST, DELETE_ACTIVITY_SUCCESS, GET_ACTIVITIES_FAIL, GET_ACTIVITIES_REQUEST, GET_ACTIVITIES_SUCCESS, GET_ACTIVITY_FAIL, GET_ACTIVITY_REQUEST, GET_ACTIVITY_SUCCESS } from "../_constants/ActivityConstants";

export const activityReducer = (state={}, action)=>{
    switch(action.type){
        case GET_ACTIVITIES_REQUEST:
            return {...state, loading:true}
        case GET_ACTIVITIES_SUCCESS:
            return {...state, loading:false, activities:action.payload}
        case GET_ACTIVITIES_FAIL:
            return {...state, loading:false, error:action.payload}

        case GET_ACTIVITY_REQUEST:
            return {...state, loading:true}
        case GET_ACTIVITY_SUCCESS:
            return {...state, loading:false, activity:action.payload}
        case GET_ACTIVITY_FAIL:
            return {...state, loading:false, error:action.payload}

        case CREATE_ACTIVITY_REQUEST:
            return {...state, loading:true}
        case CREATE_ACTIVITY_SUCCESS:
            if(state.activities.filter(activity=>activity.id===action.payload.id).length>0){
                return {...state, loading:false, activities: state.activities.map(activity=> activity.id===action.payload.id? action.payload:activity)}
            }else{
                return {...state, loading:false, activities:[...state.activities,action.payload]}
            }
        case CREATE_ACTIVITY_FAIL:
            return {...state, loading:false, error:action.payload}

        case DELETE_ACTIVITY_REQUEST:
            return {...state, loading:true}
        case DELETE_ACTIVITY_SUCCESS:
            return {...state, loading:false, activities:state.activities.filter(activity=>activity.id !== action.payload)}
        case DELETE_ACTIVITY_FAIL:
            return {...state, loading:false, error:action.payload}

    

        default:
            return state;
    }
}