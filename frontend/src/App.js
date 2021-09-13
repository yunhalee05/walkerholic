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

function App() {

  const dispatch = useDispatch()
  useEffect(() => {
    if(localStorage.getItem("walkerholic_token")){
      const token = localStorage.getItem("walkerholic_token").replace(/\"/gi, "")
      dispatch(auth(token))
    }
  }, [])
  return (
    <BrowserRouter>
      <div className="App">
          <Header/>

          <div className="main">
          <Route exact path="/" component={HomeScreen}/>
          <Route exact path="/signin" component={LoginScreen}/>
          <Route exact path="/signup" component={RegisterScreen}/>
          <Route exact path="/user/:id" component={ProfileScreen}/>
          </div>
          <Footer/>
      </div>
    </BrowserRouter>
  );
}

export default App;
