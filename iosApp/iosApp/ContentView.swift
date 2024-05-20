import SwiftUI
import shared

@available(iOS 17.0, *)
struct ContentView: View {
    var body: some View{
        HomeView()
    }
}


@available(iOS 17.0, *)
struct HomeView:View {
    let greet = Greeting().greet()
    let hello = HelloWorld().fromKotlin()
    let sample = HelloWorld().sample()
    let sum = HelloWorld().sum(value: 2, value2: 4)
    var viewModel:ProductViewModel = ProductViewModel()
    
  
    
    var body: some View {
        NavigationStack{
            
            ScrollView{
                VStack{
                    
                    Text("\(sum)")
                        .onAppear {
                            viewModel.data()
                            viewModel.dataFlow()
                        }
                    
                    LazyVStack(content: {
                        ForEach(viewModel.sharedData, id: \.self) { count in
                            Text("Placeholder \(count.brand)")
                          
                            AsyncImage(url: URL(string:count.thumbnail )) { image in
                                image.resizable()
                                    .aspectRatio(contentMode: .fit)
                            } placeholder: {
                                EmptyView()
                            }

                             
                        }
                    })
                    Divider()
                    LazyVStack(content: {
                        ForEach(viewModel.sharedData, id: \.self) { count in
                            Text("Placeholder \(count.brand)")
                        }
                    })
                }
            }
            .navigationTitle("Welcome to KMM")
           
        }
        .padding([.horizontal], 16)
        
           
            
    }
        
    
}


@available(iOS 17.0, *)
@Observable
class ProductViewModel{
   
    var sharedData:Array<Product> = []
    var sharedDataFlow:Array<Product> = []
    
    
  
    func data(){
      
        Task{
          
            let products = try await RepositoryHelper.init().products()
            
            sharedData.append(contentsOf: products)
        }
    }
    
    func dataFlow(){
        Task{
            for await product in  RepositoryHelper.init().productsFlow(){
                sharedDataFlow.append(contentsOf: product)
            }
          
        }
    }
}

@available(iOS 17.0, *)
struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
