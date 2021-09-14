import{combineReducers, createStore, compose, applyMiddleware} from 'redux';
import thunk from 'redux-thunk'
import { authReducer } from './AuthReducers';
import { discoverReducer } from './PostReducers';
import { profileReducer } from './ProfileReducers';

const initialState = {

}
// const initialState={
//     auth : {
//         ...localStorage.getItem('token')? {token:JSON.parse(localStorage.getItem('token')} : null
//     }
// }

const reducer = combineReducers({
    auth : authReducer,
    profile : profileReducer,
    discover : discoverReducer,

})

const composeEnhancer = window.__REDUX_DEVTOOLS_EXTENSION_COMPOSE__||compose;
const store = createStore(reducer, initialState, composeEnhancer(applyMiddleware(thunk)));
export default store;