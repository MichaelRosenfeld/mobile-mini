import {Component} from "react";

class App extends Component {
    state = {
        cars: []
    };

    async componentDidMount() {
        const response = await fetch('/cars');
        const body = await response.json();
        this.setState({cars: body});
    }

    render() {
        const {cars} = this.state;
        return (<div className="App">
                <header className="App-header">
                    <div className="App-intro">
                        <h2>Cars for Sale</h2>
                        {cars.map(car => <div key={car.id}>
                            {car.make}
                            {car.model}
                            {car.constructionYear}
                            {car.description}
                            {car.price}
                            {car.email}
                        </div>)}
                    </div>
                </header>
            </div>);
    }
}

export default App;