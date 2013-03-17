(ns boids.view)

(defn render-boid
  "Renders a boid, complete with placement and rotation"
  [ctx boid]
  (let [[x y] (:pos boid)
        [dx dy] (:vel boid)
        color (:color boid)
        angle (Math/atan2 dy dx)]
    (.save ctx)
    (.translate ctx x y)
    (.rotate ctx angle)
    (.beginPath ctx)
    (set! (.-strokeStyle ctx) color)
    (set! (.-fillStyle ctx) color)
    (.fillText ctx (.fromCharCode js/String 9992) 5 0) 
    (set! (.-lineWidth ctx) 1)

    (.stroke ctx)
    (.restore ctx)))

(defn render
  "Given a collection of boids, render them to the canvas"
  [canvas flock]
  (let [ctx (.getContext canvas "2d")]
    (.clearRect ctx 0 0 (.-width canvas) (.-height canvas))
    (doseq [boid flock]
      (render-boid ctx boid))))

;; in view.cljs
(defn init
  "Given an atom containing a seq of boids, initialize the view"
  [flock-atom]
  (let [canvas (.createElement js/document "canvas")]
    (.setAttribute canvas "width" (.-innerWidth js/window))
    (.setAttribute canvas "height" (.-innerHeight js/window))
    (.console.log js/window "View:init")
    (.appendChild (.-body js/document) canvas)
    (add-watch flock-atom :renderer (fn [_ _ _ flock]
                                      (render canvas flock)))))
