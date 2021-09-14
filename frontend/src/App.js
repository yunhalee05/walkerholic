import {BrowserRouter , Route} from 'react-router-dom'
import Header from './components/header/Header';
import Footer from './components/Footer';
import HomeScreen from './screens/HomeScreen';
import LoginScreen from './screens/LoginScreen';
import RegisterScreen from './screens/RegisterScreen';
import ProfileScreen from './screens/ProfileScreen';
import { useEffect } from 'react';
import { useDispatch } from 'react-redux';
import { auth } from './_actions/AuthActions';
import axios from 'axios';
import { GET_AUTH_FOLLOWS } from './_constants/AuthConstants';
import DiscoverScreen from './screens/DiscoverScreen';

function App() {

  const dispatch = useDispatch()
  
  useEffect(() => {
    if(localStorage.getItem("walkerholic_token")){
      const token = localStorage.getItem("walkerholic_token").replace(/\"/gi, "")
      dispatch(auth(token)).then(async(id)=>{
        const res = await axios.get(`/follows/${id}`)
        dispatch({
          type:GET_AUTH_FOLLOWS,
          payload:res.data
        })
      })
    }
  }, [dispatch])

  return (
    <BrowserRouter>
      <div className="App">
          <Header/>

          <div className="main">
          <Route exact path="/" component={HomeScreen}/>
          <Route exact path="/signin" component={LoginScreen}/>
          <Route exact path="/signup" component={RegisterScreen}/>
          <Route exact path="/user/:id" component={ProfileScreen}/>
          <Route exact path="/posts" component={DiscoverScreen}/>
          
          </div>
          <Footer/>
      </div>
    </BrowserRouter>
  );
}

export default App;
