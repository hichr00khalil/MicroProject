const http = require('http');

// URL de l'endpoint à tester
const url = 'http://localhost:8086/notification/5/send-email';

// Options de la requête
const options = {
  method: 'GET',
  headers: {
    'Content-Type': 'application/json',
    'Accept': 'application/json'
  }
};

// Fonction pour faire la requête
function testEndpoint() {
  console.log(`Testing endpoint: ${url}`);
  
  const req = http.request(url, options, (res) => {
    console.log(`Status Code: ${res.statusCode}`);
    console.log(`Status Message: ${res.statusMessage}`);
    console.log('Headers:', res.headers);
    
    let data = '';
    
    res.on('data', (chunk) => {
      data += chunk;
    });
    
    res.on('end', () => {
      console.log('Response Body:', data);
      console.log('Test completed.');
    });
  });
  
  req.on('error', (error) => {
    console.error('Error:', error.message);
    console.log('Test failed.');
  });
  
  req.end();
}

// Exécuter le test
testEndpoint();
