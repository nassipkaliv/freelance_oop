import { Routes, Route } from 'react-router-dom';
import { Layout } from './components/layout/Layout';
import { Home } from './pages/Home';
import { Freelancers } from './pages/Freelancers';
import { Jobs } from './pages/Jobs';

function App() {
  return (
    <Layout>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/freelancers" element={<Freelancers />} />
        <Route path="/jobs" element={<Jobs />} />
      </Routes>
    </Layout>
  );
}

export default App;
