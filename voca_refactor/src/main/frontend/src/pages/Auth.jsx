import { useNavigate } from 'react-router-dom';

import LoginMain from '../components/login/LoginForm';
import { defaultInstance } from '../util/api';
import { useDispatch } from 'react-redux';
import { SET_TOKEN } from '../reducers/jwtReducer';

export default function Auth() {
  const navigate = useNavigate();
  const dispatch = useDispatch();

  const handleSubmit = (formData) => {
    const tokenUrl = '/auth';

    defaultInstance
      .post(tokenUrl, formData)
      .then(function (res) {
        const accessToken = res.headers['authorization'];
        // RefreshToken은 서버에서 Cookie에 저장
        dispatch(SET_TOKEN(accessToken));
        localStorage.setItem('token', accessToken);
        navigate('/main');
      })
      .catch(function (e) {
        console.log(e);
      });
  };

  return (
    <>
      <LoginMain onSubmit={handleSubmit}>
        {/* {isPending && 'Submitting...'}  */}
        <button type="submit">Login</button>
      </LoginMain>
    </>
  );
}
